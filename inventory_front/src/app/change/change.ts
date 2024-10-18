import {Asset} from '../asset/asset';

export interface Change {
  id: Number,
  asset: Asset,
  description: String,
  date: Date,
  value: Number,
}
