import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {FormControl} from "@angular/forms";
import {startWith} from "rxjs/operators";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  selectedClient : Client;

  formSearch = new FormControl();

  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator : MatPaginator;
  @ViewChild(MatSort) sort : MatSort;

  constructor(private clientService : ClientService) {}

  ngOnInit(): void {
    this.clientService.getClients()
      .subscribe(clients => {
        this.dataSource.data = clients;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSource.filter = '';
      });

    setTimeout(() => {
      this.formSearch.valueChanges.subscribe(search => this.dataSource.filter = search);
    })

  }

  onSelected(client : Client) : void {
    this.selectedClient = client;
  }
}
