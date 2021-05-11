import {Component, OnInit} from '@angular/core';
import {TicketService} from "../shared/ticket.service";

@Component({
  selector: 'app-ticket-statistics',
  templateUrl: './ticket-statistics.component.html',
  styleUrls: ['./ticket-statistics.component.css']
})
export class TicketStatisticsComponent implements OnInit{

  chartLabels : string[] = [];
  chartData : number[] = []
  chartColors: any[] = [
    {
      backgroundColor:["#e25669",
        "#e28956",
        "#e2cf56",
        "#afe256",
        "#68e256",
        "#56e289",
        "#56e2cf",
        "#56afe2",
        "#5669e2",
        "#8a56e2",
        "#cf56e2",
        "#e256ae",]
    }];
  chartType = 'doughnut';

  constructor(private ticketService : TicketService) {}

  ngOnInit() {
    this.ticketService.getStatistics()
      .subscribe(statistics => {
        statistics.forEach(e => {this.chartLabels.push(e.x); this.chartData.push(e.y)})
      })
  }
}
