import Link from "next/link";

const pages = [
  { href: "/class-2/level-0", label: "Nível 0" },
  { href: "/class-2/level-0/rian", label: "Nível 0 (name)" },
  { href: "/class-2/level-1", label: "Nível 1" },
  { href: "/class-2/level-2", label: "Nível 2" },
  { href: "/class-3/client-side", label: "Aula 03 - client-side" },
  { href: "/class-3/sever-side", label: "Aula 03 - server-side" },
  { href: "/class-3/community-libraries", label: "Aula 03 - community-libraries" }
];

const Home = () => (
  <nav className="flex min-h-screen items-center justify-center bg-zinc-50 font-sans dark:bg-black">
    <ul className="flex flex-col gap-4 text-lg">
      {pages.map(({ href, label }) => (
        <li key={href}>
          <Link className="underline" href={href}>
            {label}
          </Link>
        </li>
      ))}
    </ul>
  </nav>
);

export default Home;