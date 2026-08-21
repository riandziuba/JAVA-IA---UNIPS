'use client'
import useSWR from 'swr'
import { fetcher } from './fetchData'


export default function BlogPage() {
    const { data, error, isLoading } = useSWR(
        'https://api.vercel.app/blog',
        fetcher,
        {
            refreshInterval: 10000
        }
    )

    if (isLoading) return <div>Loading...</div>
    if (error) return <div>Error: {error.message}</div>

    return (
        <ul>
            {data.map((post: { id: string; title: string }) => (
                <li key={post.id}>{post.title}</li>
            ))}
        </ul>
    )
}