import {Person} from '../person/person';
import {Asset} from '../asset/asset';

export interface InventoryField {
  id: Number,
  number: String,
  person: Person,
  assets: Array<Asset>,
}
