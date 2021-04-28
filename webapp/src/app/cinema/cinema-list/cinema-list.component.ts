import {ViewChild, Component, OnInit} from '@angular/core';
import {Cinema} from "../shared/cinema.model";
import {CinemaService} from "../shared/cinema.service";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {FormControl} from "@angular/forms";
import {Sort} from "@angular/material/sort";

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.css']
})
export class CinemaListComponent implements OnInit{

  selectedCinema : Cinema = new Cinema();

  formSearch = new FormControl();

  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator : MatPaginator;

  constructor(private cinemaService : CinemaService) {}

  ngOnInit(): void {
    this.cinemaService.getCinemas()
      .subscribe(cinemas => {
        this.dataSource.data = cinemas;
        this.dataSource.paginator = this.paginator;
      });

    setTimeout(() => {
      this.formSearch.valueChanges.subscribe(search => {
        if (search != '') {
          this.cinemaService.getFilteredCinemas(search)
            .subscribe(cinemas => {this.dataSource.data = cinemas; console.log(cinemas)});
        }
        else
        {
          this.cinemaService.getCinemas()
            .subscribe(cinemas => {this.dataSource.data = cinemas; console.log(cinemas)});
        }
      });
    })
  }

  onSelected(cinema : Cinema) : void {
    this.selectedCinema = cinema;
  }

  sortData(sort: Sort) {

    if (!sort.active || sort.direction === '') {
      return;
    }

    switch (sort.active) {
      case 'name':
        this.cinemaService.getSortedCinemasByName(sort.direction)
          .subscribe(cinemas => {
            this.dataSource.data = cinemas;
            console.log(cinemas)
          });
        return;

      case 'address':
        this.cinemaService.getSortedCinemasByAddress(sort.direction)
          .subscribe(cinemas => {
            this.dataSource.data = cinemas;
            console.log(cinemas)
          });
        return;

      default:
        return;
    }

  }
}
