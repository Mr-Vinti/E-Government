import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PaapDetail } from '../../../shared/models/paap-detail.model';

@Component({
  selector: 'app-paap-details-dialog',
  templateUrl: './paap-details-dialog.component.html',
  styleUrls: ['./paap-details-dialog.component.scss']
})
export class PaapDetailsDialogComponent implements OnInit, AfterViewInit {
  displayedColumns: String[] = [
    'id',
    'planId',
    'code',
    'name',
    'sysProcedureType',
    'sysAquisitionContractType',
    'cpvCode',
    'estimatedValueRonVat',
    'estimatedValueRon',
    'estimatedValueEur',
    'consumedValueRon',
    'startEstimatedDateTime',
    'endEstimatedDateTime',
    'personResponsable',
    'sysFinancingType',
    'responsibleUnit',
    'uniqueComplexCode',
  ];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource = new MatTableDataSource<PaapDetail>();

  constructor(public dialogRef: MatDialogRef<PaapDetailsDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.dataSource.data = this.data.paapDetails;
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  onConfirm(): void {
    this.dialogRef.close(true);
  }

  onDismiss(): void {
    this.dialogRef.close(false);
  }

}

