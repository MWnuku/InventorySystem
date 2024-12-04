import {Person} from './person';
import {
  AssetStatus
} from './asset_status';
import {Room} from './room';
import {
  InventoryField
} from './inventory-field';
import {TypeEnum} from '../enumes/typeEnum';

export interface Asset {
  id?: number | null;
  person?: Person;
  inventoryNumber?: number | null; // Allow null values
  name?: string;
  value?: number;
  date?: Date | string;
  adnotations?: string;
  status?: AssetStatus;
  room?: Room;
  inventoryField?: InventoryField;
  type?: TypeEnum;
}

