import {Asset} from '../asset/asset';

export interface Deletion {
  id: Number,
  asset: Asset,
  description: String,
  date: Date,
  value: Number,
}
