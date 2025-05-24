"use client";

import * as React from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import Link from "next/link";

export default function EmailVerificationPage() {
  return (
    <div className="flex min-h-svh w-full items-center justify-center p-6 md:p-10">
      <form className="w-full max-w-sm rounded-xl border border-muted-foreground p-10">
        <div className="flex flex-col gap-6">
          <div className="flex flex-col gap-1">
            <h1 className="text-lg font-bold">Verify Your Email</h1>
            <p className="text-xs font-semibold text-muted-foreground">
              Please enter the 6-digit code sent to your email address.
            </p>
          </div>

          <div className="grid gap-2">
            <Label htmlFor="verification-code" className="font-semibold">
              Verification Code
            </Label>
            <Input
              id="verification-code"
              type="text"
              variant="custom"
              placeholder="e.g. 123456"
              required
              minLength={6}
              maxLength={6}
            />
          </div>

          <Button
            type="submit"
            className="w-full"
            disabled={status === "loading"}
          >
            Verify
          </Button>
        </div>

        <div className="mt-4 text-center text-sm font-semibold">
          Didn&apos;t receive the code?{" "}
          <button
            type="button"
            disabled={status === "loading"}
            className="text-primary underline underline-offset-4 hover:text-primary/80 disabled:cursor-not-allowed disabled:opacity-50"
          >
            Resend Code
          </button>
        </div>
      </form>
    </div>
  );
}
