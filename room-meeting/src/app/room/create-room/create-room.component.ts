import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from 'src/app/core/room.service';
import { Room } from 'src/app/shared/models/room';

@Component({
  selector: 'app-create-room',
  templateUrl: './create-room.component.html',
  styleUrls: ['./create-room.component.scss']
})
export class CreateRoomComponent implements OnInit {

  room!:Room;
  id!:number;
  submitted!:boolean;
  viewOnly!:boolean;
  buttonSubmit!:string;

  constructor(private roomService:RoomService,
              private router:Router,
              private activatedRoute:ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.id=this.activatedRoute.snapshot.params['id'];
    console.log("Create room:" + this.id);
    console.log("Location false:" + this.location.path(false).split('/')[1]);
    if(this.id){
      this.roomService.get(this.id).subscribe((roomGet:Room)=>{
        this.room=roomGet;
        });

      if( this.location.path(false).split('/')[1]==='update'){
        this.viewOnly=false;
        this.buttonSubmit="Salvar";
      } else {
        this.viewOnly=true;
        this.buttonSubmit="Voltar";
      }
    } else {
      this.room=this.createEmptyRoom();
      this.viewOnly=false;
      this.buttonSubmit="Incluir";
    }
    this.submitted=false;
  }

  private createEmptyRoom(): Room {
    return {
      id: undefined,
      name: undefined,
      date: undefined,
      startHour: undefined,
      endHour: undefined
    } as Room;
  }

  save(){
    console.log(this.room);
    console.log(this.id);
    if(this.id){
      this.roomService.update(this.room)
      .subscribe(
        data=> console.log(data),
        error => console.log(error)
        );
    } else {
      this.roomService.save(this.room)
      .subscribe(
        data=> console.log(data),
        error => console.log(error)
        );
    }
    this.gotoList();
  }

  onSubmit(){
    this.submitted=true;
    this.save();
  }

  gotoList(){
    this.router.navigate(['/rooms']);
  }

}
