import {Asset} from './asset';

export interface Room {
  id?: number;
  asset?: Asset;
  building: string;
  symbol: string;
  dateFrom: Date;
  dateTo?: Date | null;
}

