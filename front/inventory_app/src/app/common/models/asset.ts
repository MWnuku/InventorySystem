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
  id: number;
  person: Person;
  symbol: string;
  inventoryNumber: number;
  name: string;
  value: number;
  date: Date;
  adnotations?: string;
  status: AssetStatus;
  room: Room;
  inventoryField: InventoryField;
  type?: TypeEnum;
}
