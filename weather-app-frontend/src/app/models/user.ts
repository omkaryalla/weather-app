export class User {
    constructor(
        public userName: string,
        public password: string,
        public token: string,
        public watchList: string[]
    ) {}
}
