import {
  InventoryField
} from './inventory-field';
import {Role} from './role';

export interface Person {
  id: number | null | undefined;
  firstName?: string; // Optional
  lastName?: string; // Optional
  email?: string; // Optional
  password?: string; // Optional
  unit?: string; // Optional
  inventoryFields?: InventoryField[]; // Optional
  role?: Role; // Optional
}
