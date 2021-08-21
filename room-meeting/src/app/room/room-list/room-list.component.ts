import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RoomService } from 'src/app/core/room.service';
import { Room } from 'src/app/shared/models/room';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent implements OnInit {

  rooms!:Room[];
  listAll:boolean=false;

  constructor(private roomService: RoomService,
              private router:Router) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData(){
    this.roomService.listAll().subscribe(
      (roomAll: Room[])=>{
        this.rooms=roomAll;
        this.listAll=true;
      },
      (error) =>{
        this.listAll=false;
        this.rooms=[];
      }
      );

  }

  deleteRoom(id?: number){
    this.roomService.delete(id!)
    .subscribe(
      data=>this.rooms=this.rooms.filter((roomDel)=>roomDel.id!=id),
      error=> console.log("Error Delete")
    );
  }

  roomDetails(id?:number){
    this.router.navigate(['details',id]);
  }


  updateRoom(id?:number){
    this.router.navigate(['update',id]);
  }

  addRoom(id?:number){
    this.router.navigate(['add']);
  }

}
