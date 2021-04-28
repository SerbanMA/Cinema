import {Component, OnInit, ViewChild} from '@angular/core';
import {Client} from "../../client/shared/client.model";
import {FormControl} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  selectedMovie : Movie;

  formSearch = new FormControl();

  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator : MatPaginator;
  @ViewChild(MatSort) sort : MatSort;

  constructor(private movieService : MovieService) {}

  ngOnInit(): void {
    this.movieService.getMovies()
      .subscribe(movies => {
        this.dataSource.data = movies;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.filter = '';
      });

    setTimeout(() => {
      this.formSearch.valueChanges.subscribe(search => this.dataSource.filter = search);
    })

  }

  onSelected(movie : Movie) : void {
    this.selectedMovie = movie;
  }

}
