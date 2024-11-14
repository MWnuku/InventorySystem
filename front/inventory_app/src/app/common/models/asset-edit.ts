import { Person } from './person';
import { AssetStatus } from './asset_status';
import { Room } from './room';
import { InventoryField } from './inventory-field';
import { TypeEnum } from '../enumes/typeEnum';

export interface EditAsset {
  readonly id: number;
  person: Person;
  symbol: string;
  inventoryNumber: number;
  name: string;
  date: Date;
  value: number;
  adnotations?: string;
  status: AssetStatus;
  rooms: Room[];
  inventoryField: InventoryField;
  type?: TypeEnum;
}
