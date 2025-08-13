import { Component } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import {FullCalendarModule} from "@fullcalendar/angular";
@Component({
  selector: 'app-calendar',
  imports: [
    FullCalendarModule
  ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {
  date:any;

  calendarOptions: any = {
    plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth', // pornim cu vizualizare lunară
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek' // butoane pentru comutare
    },
    events: [
      { title: 'Ședință', date: '2025-08-14' },
      { title: 'Ședință1', date: '2025-08-14' },
      { title: 'Ședință32', date: '2025-08-14' },
      { title: 'Ședință4', date: '2025-08-14' },
      { title: 'Ședință4', date: '2025-08-14' },
      { title: 'Eveniment important', start: '2025-08-15T10:00:00', end: '2025-08-15T12:00:00' }
    ],
    dateClick:this.onDateClick.bind(this)
  };

  onDateClick(e: any) {
    this.date=e.date;

  }
}
