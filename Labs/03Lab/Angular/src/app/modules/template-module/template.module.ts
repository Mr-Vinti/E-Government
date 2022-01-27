import { NgModule } from '@angular/core';
import { TemplateComponent } from './template.component';

import { TemplateRouting } from './template.routing';
import { SharedModule } from '../../shared/shared.module';
import { PaapDetailsDialogComponent } from './confirm-dialog/paap-details-dialog.component';

@NgModule({
  declarations: [TemplateComponent, PaapDetailsDialogComponent],
  imports: [
    SharedModule,
    TemplateRouting,
  ],
})
export class TemplateModule {}
