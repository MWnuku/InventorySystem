import {Person} from '../person/person';
import {
  Acquisition
} from '../acquisition/acquisition';
import {Change} from '../change/change';
import {
  Deletion
} from '../deletion/deletion';
import {
  AssetStatus
} from '../AssetStatus/asset_status';
import {Room} from '../room/room';
import {
  InventoryField
} from '../inventoryField/inventory-field';
import {TypeEnum} from '../type';

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
