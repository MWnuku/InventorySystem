import {Asset} from './asset';

export interface Room {
  id: Number,
  asset: Asset,
  building: String,
  symbol: String,
  dateFrom: Date,
  dateTo: Date,
}
