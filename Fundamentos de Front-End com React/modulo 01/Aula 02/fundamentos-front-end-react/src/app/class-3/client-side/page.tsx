import { PostType } from '@/app/types'
import Posts from '@/components/class-3/Posts'
import { Suspense } from 'react'

const getPosts = async () => {
    let data = await fetch('https://api.vercel.app/blog')
    let posts: PostType[] = await data.json()

    return posts;
}

export default function Page() {
    // Don't await the data fetching function
    const posts = getPosts()

    return (
        <Suspense fallback={<div>Loading...</div>}>
            <Posts posts={posts} />
        </Suspense>
    )
}