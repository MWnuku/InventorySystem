import {Asset} from './asset';

export interface Change {
  id: Number,
  asset: Asset,
  description: String,
  date: Date,
  value: Number,
}
