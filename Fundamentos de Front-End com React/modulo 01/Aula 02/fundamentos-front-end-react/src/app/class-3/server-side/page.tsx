import { PostType } from "@/app/types"

export default async function Page() {
    let data = await fetch('https://api.vercel.app/blog')
    let posts: PostType[] = await data.json()
    return (
        <ul>
            {posts.map((post) => (
                <li key={post.id}>{post.title}</li>
            ))}
        </ul>
    )
}