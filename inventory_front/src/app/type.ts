export enum TypeEnum {
  Computer = 487,
  Furniture = 809,
  Measure = 664,
  Audiovisual = 662,
  Intelectual = 202
}

export const TypeDescriptions: { [key in TypeEnum]: string } = {
  [TypeEnum.Computer]: "Computer asset",
  [TypeEnum.Furniture]: "Furniture asset",
  [TypeEnum.Measure]: "Measure asset",
  [TypeEnum.Audiovisual]: "Audiovisual asset",
  [TypeEnum.Intelectual]: "Intelectual asset",
};

export function getDescription(type: TypeEnum): string {
  return TypeDescriptions[type];
}
