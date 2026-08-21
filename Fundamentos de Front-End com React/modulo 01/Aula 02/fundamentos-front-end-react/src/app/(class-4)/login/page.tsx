"use client";

import { useState } from "react";

import { useRouter } from "next/navigation";

import { Button } from "@/components/class-2/Button";

import { useAuth } from "@/context/class-4/AuthContext";

export default function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [error, setError] = useState("");

    const { login } = useAuth();

    const router = useRouter();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        setError("");

        if (!email || !password) {
            setError("Preencha o formulário para continuar");

            return;
        }

        try {
            await login(email, password);

            router.push("/dashboard");
        } catch (err) {
            setError((err as Error).message);
        }
    };

    return (
        <div className="grid gap-y-4 w-96 p-4 border border-gray-900 rounded">
            <h1 className="text-3xl font-bold">Login</h1>

            {error && (
                <p className="p-2 bg-red-900 rounded text-white font-bold">{error}</p>
            )}

            <form onSubmit={handleSubmit} className="grid gap-y-2">
                <input
                    className="p-2 border border-gray-900 rounded"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email"
                />

                <input
                    className="p-2 border border-gray-900 rounded"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Senha"
                    type="password"
                />

                <Button type="submit">Entrar</Button>
            </form>
        </div>
    );
}