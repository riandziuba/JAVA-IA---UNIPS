export const fetchWithToken = async (
    url: string,
    token: string,
    options: RequestInit = {}
) =>
    await fetch(url, {
        ...options,
        headers: {
            ...options.headers,
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
        },
    });