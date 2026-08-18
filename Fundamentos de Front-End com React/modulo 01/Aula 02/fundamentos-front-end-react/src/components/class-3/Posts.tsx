'use client'
import { PostType } from '@/app/types'
import { use } from 'react'

type PostProps = { posts: Promise<PostType[]> };

export default function Posts({ posts }: PostProps) {
    const allPosts = use(posts)

    return (
        <ul>
            {allPosts.map((post) => (
                <li key={post.id}>{post.title}</li>
            ))}
        </ul>
    )
}