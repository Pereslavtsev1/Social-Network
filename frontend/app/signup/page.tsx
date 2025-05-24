import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import Link from "next/link";
import * as React from "react";

export default function Signup() {
  return (
    <div className="flex min-h-svh w-full items-center justify-center p-6 md:p-10">
      <form className="w-full max-w-sm rounded-xl border border-muted-foreground p-10 shadow-xl">
        <div className="flex flex-col gap-6">
          <div className="flex flex-col gap-1">
            <h1 className="text-lg font-bold">Sign up</h1>
            <p className="text-xs font-semibold text-muted-foreground">
              Enter your information below to create an account
            </p>
          </div>
          <div className="grid gap-2">
            <Label htmlFor="confirm-password" className="font-semibold">
              Username
            </Label>
            <Input id="username" type="text" variant="custom" required />
          </div>
          <div className="grid gap-2">
            <Label htmlFor="email" className="font-semibold">
              Email
            </Label>
            <Input
              id="email"
              type="email"
              variant="custom"
              placeholder="m@example.com"
              required
            />
          </div>
          <div className="grid gap-2">
            <Label htmlFor="password" className="font-semibold">
              Password
            </Label>
            <Input id="password" type="password" variant="custom" required />
          </div>
          <Button type="submit" className="w-full">
            Sign up
          </Button>
        </div>
        <div className="mt-4 text-center text-sm font-semibold">
          Already have an account?{" "}
          <Link href="/login" className="underline underline-offset-4">
            Login
          </Link>
        </div>
      </form>
    </div>
  );
}
