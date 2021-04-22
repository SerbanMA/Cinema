import {ViewChild, Component, OnInit, AfterViewInit} from '@angular/core';
import {Cinema} from "../shared/cinema.model";
import {CinemaService} from "../shared/cinema.service";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.css']
})
export class CinemaListComponent implements OnInit{

  cinemas : Cinema[];
  cinemasSlice : Cinema[]

  selectedCinema : Cinema = new Cinema();

  constructor(private cinemaService : CinemaService) {
  }

  ngOnInit(): void {
    this.cinemaService.getCinemas()
      .subscribe(cinemas => {this.cinemas = cinemas; this.cinemasSlice = this.cinemas.slice(0, 5);});
  }

  onSelected(cinema : Cinema) : void {
    this.selectedCinema = cinema;
    console.log(cinema)
  }

  onPageChange(event : PageEvent) {
    const startIndex = event.pageIndex * event.pageSize
    let endIndex = startIndex + event.pageSize;
    if (endIndex > this.cinemas.length){
      endIndex = this.cinemas.length
    }
    this.cinemasSlice=this.cinemas.slice(startIndex, endIndex)
  }



}
