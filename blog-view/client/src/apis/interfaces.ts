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
