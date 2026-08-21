import { NextRequest, NextResponse } from "next/server";

import { jwtVerify } from "jose";

const secret = new TextEncoder().encode(process.env.JWT_SECRET);

export async function middleware(req: NextRequest) {
    const token = req.cookies.get("token")?.value;

    if (!token) {
        return NextResponse.redirect(new URL("/login", req.url));
    }

    try {
        const { payload } = await jwtVerify(token, secret);

        const role = payload.role;

        if (req.nextUrl.pathname.startsWith("/dashboard") && role !== "admin") {
            return NextResponse.redirect(new URL("/unauthorized", req.url));
        }

        return NextResponse.next();
    } catch {
        return NextResponse.redirect(new URL("/login", req.url));
    }
}

export const config = {
    matcher: ["/dashboard"],
};