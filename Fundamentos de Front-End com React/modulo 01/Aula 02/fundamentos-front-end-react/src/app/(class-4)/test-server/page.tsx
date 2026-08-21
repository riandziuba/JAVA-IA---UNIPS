import { cookies } from "next/headers";

import { fetchWithToken } from "@/lib/class-4/fetchWithToken";

export default async function Page() {
    const cookieStore = await cookies();

    const token = cookieStore.get("token")?.value;

    if (!token) {
        return <div>Nenhum token encontrado</div>;
    }

    const response = await fetchWithToken(
        "http://localhost:3000/api/protected",
        token
    );

    const data = await response.json();

    return <div>{JSON.stringify(data)}</div>;
}