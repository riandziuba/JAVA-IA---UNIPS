"use client";

import { createContext, useState, useContext, useEffect } from "react";

import { useRouter } from "next/navigation";

import Cookies from "js-cookie";

import { decodeJwt } from "jose";

export type User = {
    email: string;
    role: "user" | "admin";
};

type AuthContextProps = {
    token: string | null;
    user: User | null;
    login: (email: string, password: string) => Promise<void>;
    logout: () => void;
};

const AuthContext = createContext({} as AuthContextProps);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<User | null>(null);

    const router = useRouter();

    useEffect(() => {
        const savedToken = Cookies.get("token");

        if (savedToken) {
            const { email, role } = decodeJwt(savedToken) as unknown as User;

            setToken(savedToken);
            setUser({ email, role });
        }
    }, []);

    const login = async (email: string, password: string) => {
        const res = await fetch("/api/auth", {
            method: "POST",
            body: JSON.stringify({ email, password }),
            headers: {
                "Content-Type": "application/json",
            },
        });

        const data = await res.json();

        if (res.ok) {
            setUser(data.user);
        } else {
            throw new Error(data.message);
        }
    };

    const logout = () => {
        setToken(null);
        setUser(null);

        Cookies.remove("token");

        router.push("/login");
    };

    return (
        <AuthContext.Provider value={{ token, user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);