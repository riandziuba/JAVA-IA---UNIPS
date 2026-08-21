"use client";

import { useEffect, useState } from "react";

import { useAuth } from "@/context/class-4/AuthContext";

import { fetchWithToken } from "@/lib/class-4/fetchWithToken";

export default function Page() {
    const { token } = useAuth();

    const [response, setResponse] = useState();

    useEffect(() => {
        if (token) {
            (async () => {
                const res = await fetchWithToken(
                    "http://localhost:3000/api/protected",
                    token
                );

                const data = await res.json();

                setResponse(data);
            })();
        }
    }, [token]);

    if (!token) {
        return <div>Nenhum token encontrado</div>;
    }

    return <div>{JSON.stringify(response)}</div>;
}