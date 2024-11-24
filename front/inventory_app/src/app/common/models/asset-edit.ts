import { Person } from './person';
import { AssetStatus } from './asset_status';
import { Room } from './room';
import { InventoryField } from './inventory-field';
import { TypeEnum } from '../enumes/typeEnum';

export interface EditAsset {
  id: number;
  date: Date;
  value: number;
  room: { building: string; symbol: string; dateFrom: Date; dateTo?: Date | null };
  person: { id: number };
  symbol: string;
  inventoryNumber: number;
  name: string;
  acquisitions: any[];
  changes: any[];
  deletions: any[];
  adnotations: string;
  status: string;
  rooms: { building: string; symbol: string; dateFrom: Date; dateTo?: Date | null }[];
  inventoryField: { id: number };
  type?: string | null;
}

