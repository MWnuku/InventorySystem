import {
  InventoryField
} from '../inventoryField/inventory-field';
import {Role} from '../role/role';

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
