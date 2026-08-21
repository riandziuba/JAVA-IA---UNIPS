import { NextRequest, NextResponse } from "next/server";

import { jwtVerify } from "jose";

const secret = new TextEncoder().encode(process.env.JWT_SECRET);

export async function GET(req: NextRequest) {
    const token = req.headers.get("authorization")?.replace("Bearer ", "");

    if (!token) {
        return NextResponse.json({ message: "Unauthorized" }, { status: 401 });
    }

    try {
        const { payload } = await jwtVerify(token, secret);

        return NextResponse.json({
            message: "Acesso autorizado (Autenticação OK)",
            user: payload,
        });
    } catch {
        return NextResponse.json({ message: "Token inválido" }, { status: 401 });
    }
}