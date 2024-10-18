import {Asset} from '../asset/asset';

export interface Acquisition {
  id: Number,
  asset: Asset,
  description: String,
  date: Date,
  value: Number
}
