export type PublishMode = 'published' | 'prePublish' | 'sketch' | 'recycled';
export type Visibility = 'public' | 'private' | 'protected';

export interface BlogMetadata {
    metadataId: string;
    publishDatetime: string;
    updateDatetime: string;
    authorId: string;
    title: string;
    contentSummary: string;
    publishMode: PublishMode;
    visibility: Visibility;
    accessPassword: string;
    prePublishDatetime: string;
}

export interface BlogContentData extends BlogMetadata {
    blogContent: string
}

export interface OrderItem {
    column: string,
    asc?: boolean
}

export interface Page {
    current: number,
    size: number,
    orders?: OrderItem[]
}

export interface ResponseResult<T> {
    timestamp: number;
    status: string;
    message: string;
    data: T;
}
