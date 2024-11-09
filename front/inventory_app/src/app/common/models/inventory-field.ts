import {Person} from './person';
import {Asset} from './asset';

export interface InventoryField {
  id: Number,
  number: String,
  person: Person,
  assets: Array<Asset>,
}
