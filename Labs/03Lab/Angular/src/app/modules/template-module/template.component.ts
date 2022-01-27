import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Record } from '../../shared/models/record.model';
import { CommonService } from '../../core/http/common.service';
import { saveAs } from 'file-saver';
import { DropdownItem } from '../../shared/models/dropdown-item.model';
import { Paap } from '../../shared/models/paap.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { PaapDetailsDialogComponent } from './confirm-dialog/paap-details-dialog.component';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
})
export class TemplateComponent implements OnInit {
  form: FormGroup;
  userRegistered: Boolean;
  invitationUrl: string;
  dropdownItems: DropdownItem[];
  paaps: Paap[] = [];
  displayedColumns: String[] = [
    'paapId',
    'paapCode',
    'paapName',
    'paapYear',
    'paapVersion',
    'caCIF',
    'caName',
    'publicationDate',
    'paapStateName',
  ];
  dataSource = new MatTableDataSource<Paap>();

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: CommonService,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.service.getDropdown().subscribe((response) => {
      this.dropdownItems = response.items;

      this.dropdownItems = this.dropdownItems.filter((item) =>
        item.text.toLowerCase().includes('minister')
      );

      this.service
        .getPaaps(this.dropdownItems.map((item) => item.text))
        .subscribe((responsePaap) => {
          this.paaps = responsePaap;
          this.dataSource.data = this.paaps;
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
        });
    });
  }

  openPaapDetails(paapId: number) {
    this.service.getPaapDetails(paapId).subscribe((response) => {
      const paapDetails = response;
      const dialogRef = this.dialog.open(PaapDetailsDialogComponent, {
        data: {
          paapDetails,
        },
      });
    });
  }
}
