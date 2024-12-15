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
  inventoryNumber?: number | null;
  name?: string;
  value?: number;
  date?: string | number | Date;
  adnotations?: string;
  status?: AssetStatus;
  room?: Room;
  inventoryField?: InventoryField;
  type?: TypeEnum;
}

