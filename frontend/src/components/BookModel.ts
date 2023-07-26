type BookModel = {
    id: string;
    title: string;
    authors:string[] | null;
    description: string;
    imageUrl:string | null | undefined;
    price: number;
};

export default BookModel;