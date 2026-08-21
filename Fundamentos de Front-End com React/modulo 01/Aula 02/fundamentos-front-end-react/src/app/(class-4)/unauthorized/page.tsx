"use client";

import Link from "next/link";

import { useAuth } from "@/context/class-4/AuthContext";

export default function Page() {
    const { user } = useAuth();

    return (
        <div className="grid gap-y-4 w-96 p-4 border border-gray-900 rounded">
            <h1 className="text-3xl font-bold">Não autorizado</h1>

            {user ? (
                <p>Acesso não autorizado (Autorização NOK)</p>
            ) : (
                <p>Acesso não autorizado (Autenticação NOK)</p>
            )}

            {user?.role && <p>Sua role: {user?.role}</p>}

            <Link
                className="border border-blue-500 p-2 rounded cursor-pointer bg-blue-500 hover:bg-blue-900 text-[#fff] font-bold text-center"
                href="/login"
            >
                Login
            </Link>
        </div>
    );
}