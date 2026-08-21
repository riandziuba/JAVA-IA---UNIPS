import { NextResponse } from "next/server";

import { SignJWT } from "jose";

import { User } from "@/context/class-4/AuthContext";

const alg = process.env.JWT_ALGORITHM!;
const secret = new TextEncoder().encode(process.env.JWT_SECRET);

const fakeUsers = ["admin@example.com", "user@example.com"];

export async function POST(req: Request) {
    const { email, password } = await req.json();

    if (fakeUsers.includes(email)) {
        if (password === "123456") {
            const user: User = { email, role: email.split("@")[0] };

            const token = await new SignJWT(user)
                .setProtectedHeader({ alg })
                .setExpirationTime("1h")
                .sign(secret);

            const response = NextResponse.json({ user });

            response.cookies.set("token", token, {
                secure: true,
                path: "/",
                maxAge: 60 * 60, // 1 hora
                // maxAge: 10 // 10 segundos
            });

            return response;
        } else {
            return NextResponse.json({ message: "Senha incorreta" }, { status: 401 });
        }
    } else {
        return NextResponse.json(
            { message: "Usuário não encontrado" },
            { status: 401 }
        );
    }
}