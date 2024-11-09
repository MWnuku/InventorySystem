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
  id: Number,
  name: String,
  person: Person,
  symbol: String,
  inventory_number: Number,
  acquisitions: Array<Acquisition>,
  changes: Array<Change>,
  deletions: Array<Deletion>,
  adnotations: String,
  asset_status: AssetStatus,
  rooms: Array<Room>,
  inventory_field: InventoryField,
  type: TypeEnum;
}
