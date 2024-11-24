import {Person} from './person';
import {Asset} from './asset';

export interface InventoryField {
  id: number | null;
  number?: string; // Optional
  person?: Person; // Optional
  assets?: Asset[]; // Optional
}
