import { DropdownItem } from './dropdown-item.model';

export interface PaapDetail {
  id: number;
  planId: number;
  code: string;
  name: string;
  sysProcedureType: DropdownItem;
  sysAquisitionContractType: DropdownItem;
  cpvCode: DropdownItem;
  estimatedValueRonVat: number;
  estimatedValueRon: number;
  estimatedValueEur: number;
  consumedValueRon: number;
  startEstimatedDateTime: Date;
  endEstimatedDateTime: Date;
  personResponsable: string;
  sysFinancingType: DropdownItem;
  responsibleUnit: string;
  uniqueComplexCode: string;
}
