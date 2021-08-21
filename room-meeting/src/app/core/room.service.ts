import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from '../shared/models/room';


const url = 'http://localhost:8081/api/v1/room/';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) {
  }

  save(room: Room): Observable<Room> {
    console.log("log service" + room);
    return this.http.post<Room>(url, room);
  }

  update(room: Room): Observable<Room> {
    return this.http.put<Room>(url + room.id, room);
  }

  listAll(): Observable<Room[]> {
    return this.http.get<Room[]>(url);
  }

  get(id: number): Observable<Room> {
    return this.http.get<Room>(url + id);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(url + id);
  }

}
