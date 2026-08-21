"use client";

import { Button } from "@/components/class-2/Button";

import { useAuth } from "@/context/class-4/AuthContext";

export default function Dashboard() {
    const { user, logout } = useAuth();

    if (!user) return null;

    return (
        <div className="grid gap-y-4 w-96 p-4 border border-gray-900 rounded">
            <h1 className="text-3xl font-bold">Dashboard</h1>

            <p>Acesso autorizado (Autorização OK)</p>

            <p>Sua role: {user.role}</p>

            <Button onClick={logout}>Logout</Button>
        </div>
    );
}