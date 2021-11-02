import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Record } from '../../shared/models/record.model';
import { CommonService } from '../../core/http/common.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
})
export class TemplateComponent implements OnInit {
  form: FormGroup;
  userRegistered: Boolean;
  invitationUrl: string;

  constructor(private service: CommonService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.email, Validators.required]],
      phoneNumber: [
        '',
        [Validators.required, Validators.pattern('[- +()0-9]+')],
      ],
      address: ['', [Validators.required]],
      payment: ['', [Validators.required]],
      paymentWithVat: [{ value: '', disabled: true }, [Validators.required]],
    });
  }

  calculatePaymentWithVat(): void {
    this.form.controls.paymentWithVat.setValue(
      Number(this.form.controls.payment.value) * 1.19
    );
  }

  private getDateTime(): string {
    const date = new Date();
    const dd = String(date.getDate()).padStart(2, '0');
    const mm = String(date.getMonth() + 1).padStart(2, '0');
    const yyyy = date.getFullYear();

    return mm + dd + yyyy;
  }

  private generatePaymentOrder(record: Record): void {
    let fileContent =
      'Payment Order emitted for ' +
      record.firstName +
      ' ' +
      record.lastName +
      '.\r\n';
    fileContent +=
      'Customer contact data\r\nEmail: ' +
      record.email +
      '\r\nPhone Number: ' +
      record.phoneNumber +
      '\r\nAddress: ' +
      record.address +
      '\r\n';

    fileContent +=
      'The customer has to pay the following sum, with VAT already applied: ' +
      record.paymentWithVat;

    const blob = new Blob([fileContent], { type: 'text/plain;charset=utf-8' });
    saveAs(blob, 'paymentOrder' + '_' + this.getDateTime() + '.txt');
  }

  onSubmit(): void {
    if (!this.form.valid) {
      this.form.markAllAsTouched();
    }

    const record: Record = {
      firstName: this.form.controls.firstName.value,
      lastName: this.form.controls.lastName.value,
      email: this.form.controls.email.value,
      phoneNumber: this.form.controls.phoneNumber.value,
      address: this.form.controls.address.value,
      payment: this.form.controls.payment.value,
      paymentWithVat: this.form.controls.paymentWithVat.value,
    };
    this.service.addRecord(record).subscribe(() => {
      this.generatePaymentOrder(record);
      this.form.reset();
    });
  }

  onExport(): void {
    this.service.getRecords().subscribe((response) => {
      const blob = new Blob([response.response], { type: 'application/xml' });
      saveAs(blob, 'dataExport' + '_' + this.getDateTime() + '.xml');
    });
  }
}
