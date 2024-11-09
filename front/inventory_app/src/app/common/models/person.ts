import {
  InventoryField
} from './inventory-field';
import {Role} from './role';

export interface Person {
  id: Number,
  firstName: String,
  lastName: String,
  email: String,
  password: String,
  unit: String;
  inventoryFields: Array<InventoryField>,
  role: Role,
}
