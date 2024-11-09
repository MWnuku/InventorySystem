import {Person} from './person';
import {
  Acquisition
} from './acquisition';
import {Change} from './change';
import {
  Deletion
} from './deletion';
import {
  AssetStatus
} from './asset_status';
import {Room} from './room';
import {
  InventoryField
} from './inventory-field';
import {TypeEnum} from '../enumes/typeEnum';

export interface Asset {
  id: number;
  person: Person;
  symbol: string;
  inventoryNumber: number;
  name: string;
  acquisitions: Acquisition[];
  changes: Change[];
  deletions: Deletion[];
  adnotations?: string;
  status: AssetStatus;
  rooms: Room[];
  inventoryField: InventoryField;
  type?: TypeEnum;
}
