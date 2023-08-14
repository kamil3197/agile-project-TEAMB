interface Band {
  name: string;
  level: number;
  responsibilities: string;
}

export default class BandValidator {
  validateBand(band: Band): string | null {
    if (band.name.length < 1) {
      return 'Name must be at least 1 characters';
    }

    if (band.level < 1 || band.level > 9) {
      return 'Level can be only 0-9';
    }

    if (band.responsibilities.length < 1) {
      return 'Responsibilities must be at least 1 character';
    }

    return null;
  }
}
